package cn.camerayuhang.cesiumwebgisserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "placemark_info")
public class Placemark {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "longitude")
  private Double longitude;

  @Column(name = "latitude")
  private Double latitude;

  @Column(name = "height")
  private Double height;

  @Column(name = "cartesian_x")
  private Double cartesian_x;

  @Column(name = "cartesian_y")
  private Double cartesian_y;

  @Column(name = "cartesian_z")
  private Double cartesian_z;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = PlacemarkImage.class)
  @JoinColumn(name = "placemarkimage_id", referencedColumnName = "id")
  private PlacemarkImage placemark_image;

  public Placemark() {

  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Placemark(String name, String description, Double longitude, Double latitude, Double height,
      Double cartesian_x, Double cartesian_y, Double cartesian_z) {
    this.name = name;
    this.description = description;
    this.longitude = longitude;
    this.latitude = latitude;
    this.height = height;
    this.cartesian_x = cartesian_x;
    this.cartesian_y = cartesian_y;
    this.cartesian_z = cartesian_z;
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

  public PlacemarkImage getPlacemark_image() {
    return placemark_image;
  }

  public void setPlacemark_image(PlacemarkImage placemark_image) {
    this.placemark_image = placemark_image;
  }

}
