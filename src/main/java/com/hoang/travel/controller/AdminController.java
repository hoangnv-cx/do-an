package com.hoang.travel.controller;



import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.model.CityModel;
import com.hoang.travel.service.IReportService;
import com.hoang.travel.service.IRoleService;
import com.hoang.travel.service.IUserService;
import com.hoang.travel.utils.*;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IReportService reportService;
    @Autowired
    importFileExcel importFileExcels;
    @Autowired
    private ImportFileExcelUser importFileExcelUser;
    @RequestMapping(value = "")
    public String welcome(Model model) {

        return "admin/welcomeAdmin";
    }

    @RequestMapping(value = "/users")
    public String listUser(Model model) {
        model.addAttribute("userlist", userService.findAll());
        model.addAttribute("username", SecurityUtils.getName());
        UserEntity userEntity = userService.findByName();
        model.addAttribute("roleList", roleService.findAllRole());
        model.addAttribute("hasLogin", userEntity);
        return "admin/userList";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("roleList", roleService.findAllRole());
        model.addAttribute("userID", userService.findUserByID(id));
        return "admin/edit";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String AddUser(Model model) {
        model.addAttribute("roleList", roleService.findAllRole());
        model.addAttribute("users", new UserEntity());
        return "admin/userAdd";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("users") UserEntity user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/userAdd";
        }
        if (userService.userNameExists(user.getUserName())) {
            bindingResult.addError(new FieldError("users", "userName", "Tên đăng nhấp đã được sử dụng"));
            return "admin/userAdd";
        }
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        user.setCreatedAt(timestamp);
        user.setRoles(user.getRoles());
        userService.addAndUpdateUser(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id,@Valid @ModelAttribute("userID") UserEntity user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/edit";
        }
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        user.setModifiedAt(timestamp);
        user.setRoles(user.getRoles());
        userService.addAndUpdateUser(user);
//        model.addAttribute("users", userService.findAll());
        return "redirect:/admin/users";
    }

    @GetMapping(value = "report/user", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReport() {
        try {
            return new ResponseEntity<byte[]>(reportService.getReport(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/users.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
        System.out.println(userService.findAll());
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(userService.findAll());
        IOUtils.copy(stream, response.getOutputStream());
    }

    @GetMapping("/download/roles.xlsx")
    public void downloadCsvRoles(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=roles.xlsx");
        System.out.println(roleService.findAll());
        ByteArrayInputStream stream = ExportRole.exportFileExcel(roleService.findAll());
        IOUtils.copy(stream, response.getOutputStream());
    }
    @RequestMapping(value = "/import/file",method = RequestMethod.GET)
    public String importFileUser(Model model) {

        return "admin/importFile";
    }
    @RequestMapping(value = "/import/file", method = RequestMethod.POST)
    public String importRole(@RequestParam("file") MultipartFile file){
        importFileExcelUser.importFileExcel(file);
        return "redirect:/admin";
    }
}
