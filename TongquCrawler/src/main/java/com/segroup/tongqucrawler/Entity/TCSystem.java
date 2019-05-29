package com.segroup.tongqucrawler.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tcsystem")
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
