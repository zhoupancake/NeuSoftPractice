package com.system.neusoftpractice.dto;


import com.system.neusoftpractice.entity.Administrator;
import com.system.neusoftpractice.entity.GridManager;
import com.system.neusoftpractice.entity.Supervisor;

public class RequestCharacterEntity {
    private String id;
    private String username;
    private String password;
    private Integer status;
    private String role;
    private String idCard;
    private String name;
    private String zone;

    public User getUser_create(){
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

    public User getUser_modify(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .status(status)
                .role(role)
                .build();
    }

    public Administrator getAdministrator_create(){
        return Administrator.builder()
                .idCard(idCard)
                .name(name)
                .build();
    }

    public Administrator getAdministrator_modify(){
        return Administrator.builder()
                .id(id)
                .idCard(idCard)
                .name(name)
                .build();
    }

    public Supervisor getSupervisor_create(){
        return Supervisor.builder()
                .idCard(idCard)
                .name(name)
                .build();
    }

    public Supervisor getSupervisor_modify(){
        return Supervisor.builder()
                .id(id)
                .idCard(idCard)
                .name(name)
                .build();
    }

    public GridManager getGridManager_create(){
        return GridManager.builder()
                .idCard(idCard)
                .name(name)
                .zone(zone)
                .build();
    }

    public GridManager getGridManager_modify(){
        return GridManager.builder()
                .id(id)
                .idCard(idCard)
                .name(name)
                .zone(zone)
                .build();
    }
}
