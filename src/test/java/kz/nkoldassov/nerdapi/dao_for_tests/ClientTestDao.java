package kz.nkoldassov.nerdapi.dao_for_tests;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientTestDao {

    @Insert("insert into client (email, password) values (#{email}, #{password})")
    void insertClientEmailPassword(@Param("email") String email,
                                   @Param("password") String password);

    @Select("select client from client order by client desc limit 1")
    Long getLastClientId();
}
