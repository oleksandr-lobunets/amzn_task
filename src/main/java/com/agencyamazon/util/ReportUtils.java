package com.agencyamazon.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class ReportUtils {
    private ReportUtils() {}

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T>T readReportFromFile(String filePath, Class<T> tClass) throws IOException {
        return objectMapper.readValue(Paths.get(filePath).toFile(), tClass);
    }
}
