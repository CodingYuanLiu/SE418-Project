package com.segroup.tongqucrawler.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@Document(collection = "tcsystem")
public class TCSystem {
    @Id
    private String tcskey;

    @Column
    private String tcsvalue;

    public String get() {
        return tcsvalue;
    }

    public void set(String value) {
        tcsvalue = value;
    }

    public String key() {
        return tcskey;
    }

    public TCSystem() {

    }

    public TCSystem(String key, String value) {
        tcskey = key;
        tcsvalue = value;
    }
}
