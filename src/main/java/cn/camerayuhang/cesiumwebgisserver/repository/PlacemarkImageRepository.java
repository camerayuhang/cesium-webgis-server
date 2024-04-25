package cn.camerayuhang.cesiumwebgisserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.camerayuhang.cesiumwebgisserver.model.PlacemarkImage;

public interface PlacemarkImageRepository extends JpaRepository<PlacemarkImage, Long> {
}