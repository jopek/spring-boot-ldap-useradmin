/*
 * Copyright 2005-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.ldap.samples.useradmin.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.samples.useradmin.domain.DepartmentRepo;
import org.springframework.ldap.samples.useradmin.domain.User;
import org.springframework.ldap.samples.useradmin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Mattias Hellborg Arthursson
 */
@RestController
@RequestMapping("/rest")
public class RestUserController {

  private final AtomicInteger nextEmployeeNumber = new AtomicInteger(10);

  @Autowired
  private UserService userService;

  @Autowired
  private DepartmentRepo departmentRepo;

  @RequestMapping(value = {"/", "/users"}, method = GET)
  public List<User> index(@RequestParam(required = false) String name) {
     if (StringUtils.hasText(name)) {
      return userService.searchByNameName(name);
    } else {
       List<User> userList = new ArrayList<User>();
       Iterable<User> all = userService.findAll();
       Iterator<User> iterator = all.iterator();
       while (iterator.hasNext()){
         User user = iterator.next();
         userList.add(user);
       }
       return userList;
     }
  }

  @RequestMapping(value = "/users/{userid}", method = GET)
  public User getUser(@PathVariable String userid) {
    return userService.findUser(userid);
  }

  @RequestMapping(value = "/users/uid/{uid}", method = GET)
  public User getUserByUid(@PathVariable String uid) {
    return userService.findUser(uid);
  }

  @RequestMapping(value = "/departments", method = GET)
  public Map<String, List<String>> getDepartments() throws JsonProcessingException {
    return departmentRepo.getDepartmentMap();
  }

  @RequestMapping(value = "/newuser", method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(User user) {
    userService.createUser(user);
  }

  @RequestMapping(value = "/users/{userid}", method = POST)
  public void updateUser(@PathVariable String userid, User user) {
    userService.updateUser(userid, user);
  }

}
