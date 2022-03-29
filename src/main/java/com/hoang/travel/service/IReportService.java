package com.hoang.travel.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface IReportService {
    public byte[] getReport() throws IOException, JRException;
}
