package com.lyzd.om.user.sdk.representation;


import lombok.Value;

/**
 * @author Thinker
 *
 */
//@NoArgsConstructor
//@Data                              //get，set
//@NoArgsConstructor                 //无参构造
//@AllArgsConstructor                //有参构造
@Value
public class UserRepresentation {
    private String id;
    private String name;
    
    // other properties ...

}
