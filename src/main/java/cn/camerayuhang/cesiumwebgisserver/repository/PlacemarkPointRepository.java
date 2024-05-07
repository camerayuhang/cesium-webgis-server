package cn.camerayuhang.cesiumwebgisserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.camerayuhang.cesiumwebgisserver.model.PlacemarkPoint;

public interface PlacemarkPointRepository extends JpaRepository<PlacemarkPoint, Long> {
}