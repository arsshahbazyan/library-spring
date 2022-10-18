package com.epam.learning.library.utils;

import java.util.HashMap;
import java.util.List;

import com.epam.learning.library.entity.User;

public interface UserUtils {

	public HashMap<String, String> userRolesInString(List<User> userList);
}
