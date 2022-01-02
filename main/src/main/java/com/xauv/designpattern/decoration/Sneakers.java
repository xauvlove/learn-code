package com.xauv.designpattern.decoration;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.Data;

/**
 * @Date 2021/11/06 17:54
 * @Author ling yue
 * @Package com.xauv.designpattern.decoration
 * @Desc
 */
@Data
public class Sneakers extends Clothes {

    private Person person;

    public Sneakers(Person person) {
        this.person = person;
    }

    @Override
    protected void decorate() {
        person.getClothes().add("Sneakers");
    }
}
