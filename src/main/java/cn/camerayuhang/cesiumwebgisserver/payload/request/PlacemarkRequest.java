package cn.camerayuhang.cesiumwebgisserver.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class PlacemarkRequest {

  private Long id;

  private String name;

  private String description;

  private Double longitude;

  private Double latitude;

  private Double height;

  private Double cartesian_x;

  private Double cartesian_y;

  private Double cartesian_z;

  private MultipartFile file;

  public PlacemarkRequest() {
  }

  public PlacemarkRequest(String name, String description, Double longitude, Double latitude, Double height,
      Double cartesian_x, Double cartesian_y, Double cartesian_z, MultipartFile file) {
    this.name = name;
    this.description = description;
    this.longitude = longitude;
    this.latitude = latitude;
    this.height = height;
    this.cartesian_x = cartesian_x;
    this.cartesian_y = cartesian_y;
    this.cartesian_z = cartesian_z;
    this.file = file;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getCartesian_x() {
    return cartesian_x;
  }

  public void setCartesian_x(Double cartesian_x) {
    this.cartesian_x = cartesian_x;
  }

  public Double getCartesian_y() {
    return cartesian_y;
  }

  public void setCartesian_y(Double cartesian_y) {
    this.cartesian_y = cartesian_y;
  }

  public Double getCartesian_z() {
    return cartesian_z;
  }

  public void setCartesian_z(Double cartesian_z) {
    this.cartesian_z = cartesian_z;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

}
