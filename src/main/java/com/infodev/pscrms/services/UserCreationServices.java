package com.infodev.pscrms.services;

import com.infodev.pscrms.entities.Privilege;
import com.infodev.pscrms.entities.Role;

import java.util.Collection;

public interface UserCreationServices {
    Privilege createPrivilegeIfNotFound(String name);
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges);
}
