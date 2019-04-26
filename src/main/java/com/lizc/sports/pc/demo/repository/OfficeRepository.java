package com.lizc.sports.pc.demo.repository;


import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.pc.demo.entity.Office;
import org.springframework.stereotype.Repository;


@Repository
public interface OfficeRepository extends BaseRepository<Office, String>
{
}
