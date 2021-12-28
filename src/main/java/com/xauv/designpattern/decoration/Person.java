package com.xauv.designpattern.decoration;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/11/06 17:49
 * @Author ling yue
 * @Package com.xauv.designpattern.decoration
 * @Desc
 */
@Data
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    private List<String> clothes = new ArrayList<>();

    public void show() {
        System.out.println(name + " dressing: " + clothes);
    }
}
