package com.xauv;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Objects;

/**
 * @Date 2022/10/16 17:49
 * @Author Administrator
 * @Package com.xauv
 * @Desc
 */
public class cc {

    private String s;

    private Integer c;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cc cc = (cc) o;
        return s.equals(cc.s) && c.equals(cc.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, c);
    }
}
