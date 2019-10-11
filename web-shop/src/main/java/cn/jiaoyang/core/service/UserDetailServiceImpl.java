package cn.jiaoyang.core.service;

import cn.jiaoyang.core.pojo.seller.Seller;
import cn.jiaoyang.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @Auther Jiao yang
 * @Date 2019/10/8 20:08
 */
public class UserDetailServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
        arrayList.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        if(username==null){
            return null;
        }
        Seller seller = sellerService.findOne(username);
        if(seller!=null){
            if("1".equals(seller.getStatus())){
                return new User(username,seller.getPassword(),arrayList);
            }
        }
        return null;
    }
}
