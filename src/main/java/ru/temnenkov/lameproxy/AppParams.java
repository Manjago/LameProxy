package ru.temnenkov.lameproxy;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class AppParams {
    @Parameter(names = "-port", description = "port", required = true)
    private int port;
    @Parameter(names = "-user", description = "user", required = true)
    private String user;
    @Parameter(names = "-pwd", description = "pwd", required = true)
    private String pwd;
}
