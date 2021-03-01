package com.example.dao;

import com.example.entity.Kuser;
import org.springframework.data.jpa.repository.JpaRepository;


/*
    별다른 어노테이션을 달지 않아도 JpaRepository를 상속하기때문에 componentScan시 빈으로 등록해준다
 */

public interface KuserRepository extends JpaRepository<Kuser,Long> {

    Kuser findByUserName (String uesrName);

}