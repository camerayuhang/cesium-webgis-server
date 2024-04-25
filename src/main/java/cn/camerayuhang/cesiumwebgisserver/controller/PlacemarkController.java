package cn.camerayuhang.cesiumwebgisserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.camerayuhang.cesiumwebgisserver.model.Placemark;
import cn.camerayuhang.cesiumwebgisserver.model.PlacemarkImage;
import cn.camerayuhang.cesiumwebgisserver.repository.PlacemarkImageRepository;
import cn.camerayuhang.cesiumwebgisserver.repository.PlacemarkRepository;
import cn.camerayuhang.cesiumwebgisserver.util.ImageUtility;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plifo")
public class PlacemarkController {

  @Autowired
  PlacemarkRepository placemarkRepository;

  @Autowired
  PlacemarkImageRepository placemarkImageRepository;

  // retrieve all Tutorials
  @GetMapping("/placemarkinfo")
  public ResponseEntity<List<Placemark>> getAllPlacemarkinfo(@RequestParam(required = false) String name) {
    try {
      List<Placemark> placemarkInfoArray = new ArrayList<Placemark>();
      if (name == null) {
        placemarkRepository.findAll().forEach(placemark -> {
          if (placemark.getPlacemark_image() != null) {
            placemark.getPlacemark_image()
                .setImage(ImageUtility.decompressImage(placemark.getPlacemark_image().getImage()));
          }
          placemarkInfoArray.add(placemark);
        });
      } else {
        placemarkRepository.findByNameContaining(name).forEach(placemark -> {
          if (placemark.getPlacemark_image() != null) {
            placemark.getPlacemark_image()
                .setImage(ImageUtility.decompressImage(placemark.getPlacemark_image().getImage()));
          }
          placemarkInfoArray.add(placemark);

        });
      }

      if (placemarkInfoArray.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(placemarkInfoArray, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/placemarkinfo/{id}")
  public ResponseEntity<Placemark> getPlacemarkById(@PathVariable("id") Long id) {
    Optional<Placemark> placemarkInfoData = placemarkRepository.findById(id);

    if (placemarkInfoData.isPresent()) {
      return new ResponseEntity<>(placemarkInfoData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("placemarkinfo/{id}")
  public ResponseEntity<Placemark> updatePlacemark(@PathVariable("id") Long id,
      @RequestParam(value = "name", required = false, defaultValue = "") String name,
      @RequestParam(value = "description", required = false, defaultValue = "") String description,
      @RequestParam(value = "longitude", required = false) Double longitude,
      @RequestParam(value = "latitude", required = false) Double latitude,
      @RequestParam(value = "height", required = false) Double height,
      @RequestParam(value = "cartesian_x", required = false) Double cartesian_x,
      @RequestParam(value = "cartesian_y", required = false) Double cartesian_y,
      @RequestParam(value = "cartesian_z", required = false) Double cartesian_z,
      @RequestParam(value = "file", required = false) MultipartFile file) {
    try {
      Optional<Placemark> placemarkInfoData = placemarkRepository.findById(id);

      if (placemarkInfoData.isPresent()) {
        Placemark _placemark = placemarkInfoData.get();
        _placemark.setName(name);
        _placemark.setDescription(description);
        if (file != null) {
          PlacemarkImage placemarkImage = new PlacemarkImage(file.getOriginalFilename(), file.getContentType(),
              ImageUtility.compressImage(file.getBytes()));
          _placemark.setPlacemark_image(placemarkImage);
        }

        // save the placemark
        _placemark = placemarkRepository.save(_placemark);
        if (_placemark.getPlacemark_image() != null) {
          _placemark.getPlacemark_image()
              .setImage(ImageUtility.decompressImage(_placemark.getPlacemark_image().getImage()));
        }

        return new ResponseEntity<>(_placemark, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @DeleteMapping("/placemarkinfo/{id}")
  public ResponseEntity<HttpStatus> deletePlacemark(@PathVariable("id") Long id) {
    try {
      placemarkRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/placemarkinfo")
  public ResponseEntity<HttpStatus> deleteAllPlacemarks() {
    try {
      placemarkRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // create new placemark
  // @RequestBody只能接受"Content-Type: application/json"，可是这个无法接受文件
  @PostMapping("/placemarkinfo")
  public ResponseEntity<Placemark> createPlacemark(@RequestParam(value = "name", defaultValue = "") String name,
      @RequestParam(value = "description", defaultValue = "") String description,
      @RequestParam("longitude") Double longitude,
      @RequestParam("latitude") Double latitude, @RequestParam("height") Double height,
      @RequestParam("cartesian_x") Double cartesian_x, @RequestParam("cartesian_y") Double cartesian_y,
      @RequestParam("cartesian_z") Double cartesian_z,
      @RequestParam(value = "file", required = false) MultipartFile file) {
    try {

      Placemark _placemark = new Placemark(name, description, longitude, latitude, height, cartesian_x, cartesian_y,
          cartesian_z);
      if (file != null) {
        PlacemarkImage placemarkImage = new PlacemarkImage(file.getOriginalFilename(), file.getContentType(),
            ImageUtility.compressImage(file.getBytes()));
        _placemark.setPlacemark_image(placemarkImage);
      }

      return new ResponseEntity<>(placemarkRepository.save(_placemark), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // upload image
  @PostMapping("/placemarkimage")
  public ResponseEntity<PlacemarkImage> uploadImage(@RequestParam("image") MultipartFile file) {
    try {
      PlacemarkImage placemarkImage = new PlacemarkImage(file.getOriginalFilename(), file.getContentType(),
          ImageUtility.compressImage(file.getBytes()));
      return new ResponseEntity<>(placemarkImageRepository.save(placemarkImage), HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/placemarkimage/{id}")
  public ResponseEntity<String> deletePlacemarkImage(@PathVariable("id") Long placemarkId) {
    try {
      Optional<Placemark> placemarkData = placemarkRepository.findById(placemarkId);
      if (placemarkData.isPresent()) {
        Placemark _placemark = placemarkData.get();
        Long id = _placemark.getPlacemark_image().getId();
        _placemark.setPlacemark_image(null);
        placemarkImageRepository.deleteById(id);
        return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Placemark with ID " + placemarkId + " not found", HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping("/placemarkimage/{id}")
  // public ResponseEntity<PlacemarkImage> getMethodName(@RequestParam String
  // param) {
  // return new String();
  // }

}
