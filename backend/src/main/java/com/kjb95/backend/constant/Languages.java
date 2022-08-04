package com.kjb95.backend.constant;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Languages {
    List<String> languages;
    Languages() {
        languages = new ArrayList<>();
        languages.add("ko");
        languages.add("en");
    }
}
