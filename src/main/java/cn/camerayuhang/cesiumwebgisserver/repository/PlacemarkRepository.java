package cn.camerayuhang.cesiumwebgisserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.camerayuhang.cesiumwebgisserver.model.Placemark;

public interface PlacemarkRepository extends JpaRepository<Placemark, Long> {

  List<Placemark> findByNameContaining(String name);

}
