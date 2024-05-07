package cn.camerayuhang.cesiumwebgisserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "placemark_point")
public class PlacemarkPoint {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "default_pixel_size")
  private Float default_pixel_size;

  @Column(name = "default_color")
  private String default_color;

  @Column(name = "default_outline_color")
  private String default_outline_color;

  @Column(name = "default_outline_width")
  private Float default_outline_width;

  @Column(name = "highlight_pixel_size")
  private Float highlight_pixel_size;

  @Column(name = "highlight_color")
  private String highlight_color;

  @Column(name = "highlight_outline_color")
  private String highlight_outline_color;

  @Column(name = "highlight_outline_width")
  private Float highlight_outline_width;

  public PlacemarkPoint() {
  }

  public PlacemarkPoint(Float default_pixel_size, String default_color, String default_outline_color,
      Float default_outline_width, Float highlight_pixel_size, String highlight_color, String highlight_outline_color,
      Float highlight_outline_width) {
    this.default_pixel_size = default_pixel_size;
    this.default_color = default_color;
    this.default_outline_color = default_outline_color;
    this.default_outline_width = default_outline_width;
    this.highlight_pixel_size = highlight_pixel_size;
    this.highlight_color = highlight_color;
    this.highlight_outline_color = highlight_outline_color;
    this.highlight_outline_width = highlight_outline_width;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getDefault_pixel_size() {
    return default_pixel_size;
  }

  public void setDefault_pixel_size(Float default_pixel_size) {
    this.default_pixel_size = default_pixel_size;
  }

  public String getDefault_color() {
    return default_color;
  }

  public void setDefault_color(String default_color) {
    this.default_color = default_color;
  }

  public String getDefault_outline_color() {
    return default_outline_color;
  }

  public void setDefault_outline_color(String default_outline_color) {
    this.default_outline_color = default_outline_color;
  }

  public Float getDefault_outline_width() {
    return default_outline_width;
  }

  public void setDefault_outline_width(Float default_outline_width) {
    this.default_outline_width = default_outline_width;
  }

  public Float getHighlight_pixel_size() {
    return highlight_pixel_size;
  }

  public void setHighlight_pixel_size(Float highlight_pixel_size) {
    this.highlight_pixel_size = highlight_pixel_size;
  }

  public String getHighlight_color() {
    return highlight_color;
  }

  public void setHighlight_color(String highlight_color) {
    this.highlight_color = highlight_color;
  }

  public String getHighlight_outline_color() {
    return highlight_outline_color;
  }

  public void setHighlight_outline_color(String highlight_outline_color) {
    this.highlight_outline_color = highlight_outline_color;
  }

  public Float getHighlight_outline_width() {
    return highlight_outline_width;
  }

  public void setHighlight_outline_width(Float highlight_outline_width) {
    this.highlight_outline_width = highlight_outline_width;
  }

}
