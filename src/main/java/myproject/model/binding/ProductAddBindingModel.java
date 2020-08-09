package myproject.model.binding;

import myproject.model.entity.Category;
import myproject.model.entity.CategoryName;
import org.hibernate.mapping.UniqueKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;


public class ProductAddBindingModel {
    private String name;
    private String description;
    private CategoryName category;
    private BigDecimal price;
    private String status;
    private String manufacturer;
    private String imageUrl;

    public ProductAddBindingModel() {
    }

    @Length(min=2,message = "Username must be more than 2 character")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=3,message = "Description must be more than 3 character")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    @DecimalMin(value = "0", message = "Enter positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @NotNull(message = "Enter valid name!")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @NotNull(message = "Enter valid name!")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NotNull(message = "Set image Url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
