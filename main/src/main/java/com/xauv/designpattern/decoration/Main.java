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
 * @Date 2021/11/06 17:55
 * @Author ling yue
 * @Package com.xauv.designpattern.decoration
 * @Desc
 */
public class Main {

    public static void main(String[] args) {
        Person xauv = new Person("xauvlove");
        Tie tie = new Tie(xauv);
        tie.decorate();
        Sneakers sneakers = new Sneakers(xauv);
        sneakers.decorate();

        xauv.show();

    }
}
