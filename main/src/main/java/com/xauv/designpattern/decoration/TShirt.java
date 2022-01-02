package com.xauv.designpattern.decoration;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/11/06 17:50
 * @Author ling yue
 * @Package com.xauv.designpattern.decoration
 * @Desc
 */
public class TShirt extends Clothes {

    private Person person;

    public TShirt(Person person) {
        this.person = person;
    }

    @Override
    protected void decorate() {
        person.getClothes().add("T-shirt");
    }
}
