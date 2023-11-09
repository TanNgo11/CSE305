/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moneymanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moneymanagement.entity.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDTO extends AbstractDTO<AccountDTO> {

	private int status;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String email;
    @JsonIgnore
    private List<RoleEntity> listRole;
   

	

 

  
    
    
    
    
}
