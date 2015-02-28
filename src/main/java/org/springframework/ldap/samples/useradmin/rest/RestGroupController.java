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

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.samples.useradmin.domain.Group;
import org.springframework.ldap.samples.useradmin.domain.GroupRepo;
import org.springframework.ldap.samples.useradmin.domain.User;
import org.springframework.ldap.samples.useradmin.service.UserService;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Mattias Hellborg Arthursson
 */
@RestController
@RequestMapping("/rest")
public class RestGroupController {

  @Autowired
  private GroupRepo groupRepo;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/groups", method = GET)
  public List<String> listGroups(ModelMap map) {
    return groupRepo.getAllGroupNames();
  }

  @RequestMapping(value = "/groups", method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void newGroup(Group group) {
    groupRepo.create(group);
  }

  @RequestMapping(value = "/groups/{name}/members", method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void addUserToGroup(@PathVariable String name, @RequestParam String userId) {
    Group group = groupRepo.findByName(name);
    group.addMember(userService.toAbsoluteDn(LdapUtils.newLdapName(userId)));

    groupRepo.save(group);
  }

  @RequestMapping(value = "/groups/{name}/members", method = DELETE)
  public void removeUserFromGroup(@PathVariable String name, @RequestParam String userId) {
    Group group = groupRepo.findByName(name);
    group.removeMember(userService.toAbsoluteDn(LdapUtils.newLdapName(userId)));

    groupRepo.save(group);
  }
}
