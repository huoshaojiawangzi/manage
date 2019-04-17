package com.lizc.sports.pc.demo.repository;


import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.pc.demo.entity.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfficeRepository extends BaseRepository<Office, String>
{
    @Query(value = "select o from Office o where o.parent is null order by o.createDate desc")
    List<Office> findRoots();
}
