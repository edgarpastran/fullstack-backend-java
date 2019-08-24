package com.edpas.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edpas.model.Menu;

public interface IMenuRepo extends JpaRepository<Menu, Integer> {

	@Query(value="select m.* from menu_role mr inner join user_role ur on ur.id_role = mr.id_role inner join menu m on m.id_menu = mr.id_menu inner join user_account u on u.id_user = ur.id_user where u.username = :username", nativeQuery = true)
	List<Object[]> listMenuByUsername(@Param("username") String username);
}
