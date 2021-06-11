package web.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class StringArrayToRoleConverter implements Converter<String[], Set<Role>> {

    @Autowired
    private RoleDao roleService;

    @Override
    public Set<Role> convert(String[] strings) {
        Set<Role> roleSet = new HashSet<>();
        for (String string : strings) {
            if (string.equals("ROLE_ADMIN")) {
                roleSet.add(roleService.getAllRoles().get(0));
            }
            if (string.equals("ROLE_USER")) {
                roleSet.add(roleService.getAllRoles().get(1));
            }
        }
        return roleSet;
    }
}
