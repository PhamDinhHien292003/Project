package com.example.vgc_project.service;

import com.example.vgc_project.DTO.RoleDTO;
import com.example.vgc_project.DTO.UserDTO;
import com.example.vgc_project.Enum.Role;
import com.example.vgc_project.entity.Roles;
import com.example.vgc_project.entity.Users;
import com.example.vgc_project.repository.RoleRepository;
import com.example.vgc_project.repository.UserRepository;
import com.example.vgc_project.service.ServiceImp.UserImp;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserImp {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> lstUserDTO = new ArrayList<>();
        for(Users users : userRepository.findAll()){

            List<RoleDTO> lstRole = new ArrayList<>() ;
            for(Roles role : users.getRoles()){
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setRole(role.getName());
                lstRole.add(roleDTO);
            }
            lstUserDTO.add(new UserDTO(users.getId() ,
                    users.getName() ,
                    users.getAge() ,
                    users.getUsername() ,
                    users.getPassword() ,
                    lstRole));
        }
        return lstUserDTO;
    }

    @Override
    public UserDTO getUserWithID(int id) {
        Optional<Users> op = Optional.ofNullable(userRepository.findById(id));
        if(op.isPresent()){
            Users users = op.get();
            return new UserDTO(
                    id,
                    users.getName() ,
                    users.getAge() ,
                    users.getUsername() ,
                    users.getPassword() );
        }
        else{
            return null ;
        }
    }


    private boolean checkFormGmail(String username){
        return username.endsWith("@Gmail.com") || username.endsWith("@gmail.com");
    }

    @Override
    public String AddUser(String name, int age, String username, String password) {
        try {
            if(userRepository.findByUsername(username) == null) {
                if(!username.isEmpty() && checkFormGmail(username)){
                    /// add user into repository
                    Users user = new Users();
                    user.setName(name);
                    user.setAge(age);
                    user.setUsername(username);
                    user.setPassword(passwordEncoder.encode(password));

                    userRepository.save(user);

                    Users newUser = userRepository.findByUsername(username);
                    logger.info(newUser.getId()+" ");
                    Roles roles = new Roles();
                    roles.setName(Role.USER.name());
                    roles.setUser(newUser);
                    List<Roles> rolesList = new ArrayList<>();
                    rolesList.add(roles);
                    user.setRoles(rolesList);
                    roleRepository.save(roles);

                    ///update user entity (add role)
                    user.setRoles(rolesList);
                    userRepository.save(user);
                }
                else {
                    return "username's incorrect format";
                }

            }
            else {
                return "username has exist" ;
            }
        }catch (Exception ex ){
            logger.info(ex.getMessage());
            return "null" ;
        }
        return "Successfully" ;
    }

    @Override
    public UserDTO checklogin(String username, String password) {
        Users users = userRepository.findByUsername(username);
        return users != null && passwordEncoder.matches(password ,users.getPassword() ) ? new UserDTO(users.getId() , users.getName() , roletoDTO(users.getRoles())) : null;
    }


    private List<RoleDTO> roletoDTO(List<Roles> lst ){
        List<RoleDTO> lstRole = new ArrayList<>();
        for(Roles role : lst ){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRole(role.getName());
            lstRole.add(roleDTO);
        }

        return  lstRole ;
    }

    @Transactional
    @Override
    public boolean delUser(int id) {
        if(userRepository.existsById(id)){
            try{
                roleRepository.deleteByUserId(id);
                userRepository.deleteById(id);
                return true ;
            }
            catch (Exception ex ){
                logger.info(ex.getMessage());
                return false;
            }

        }
        else {

            return false;
        }
    }

}
