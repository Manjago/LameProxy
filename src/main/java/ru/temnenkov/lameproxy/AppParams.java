package ru.temnenkov.lameproxy;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class AppParams {
    @Parameter(names = "-port", description = "port", required = true)
    private int port;
    @Parameter(names = "-users", description = "users", required = true)
    private String users;
}
