package com.tsubaki.repositories;

import com.tsubaki.models.MenuItem;
import com.tsubaki.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query(value = "select mi.* from menu_item mi join menu_item_user miu on mi.id = miu.menu_id and miu.menu_id = ?1 and miu.user_id = ?2", nativeQuery = true)
    Set<MenuItem> existsMenuItemByMenuUsers(Integer itemId, Integer userId);
    Set<MenuItem> findAllByIsDefaultIs(boolean isDefault);
}
