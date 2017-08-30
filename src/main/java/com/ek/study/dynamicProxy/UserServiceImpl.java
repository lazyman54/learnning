package com.ek.study.dynamicProxy;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class UserServiceImpl implements IUserService {
    @Override
    public void userTalking(String word) {
        System.out.println("say: " + word);
    }

    @Override
    public String userReject() {
        return "reject";
    }
}
