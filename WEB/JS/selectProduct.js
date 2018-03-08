$(document).ready(function(){
  const productContainer = document.getElementById("js--product-container");

  let addProduct = function(productJson) {
    let productCard =
    `<div class="grow product-box js--button-select-product" id="${productJson.id}">
    <div class="product-image">
    <img src="http://www.drawingskill.com/wp-content/uploads/1/T-Shirt-Image-Drawing.jpg">
    </div>
    <div class="product-name">${productJson.name}</div>
    </div>`;
    return productCard;
  }

  let addAllProducts = function(json) {
    console.log(json);
    for (let product of json) {
      productContainer.innerHTML += addProduct(product);
      $("body").one("click", "#" + product.id, function(event){
          event.preventDefault();
          $("section").load("viewProduct.html #js--view-product");
          productId = product.id;
          $.getScript("JS/viewProduct.js");
      });
    }
  }

  let fetchAllProducts = function() {
    fetch("http://10.114.32.58:8080/FDPM-SERVER/sources/model.product")
    .then(response => response.json())
    .then(json => addAllProducts(json))
    .catch(error => console.log(error));
  }
  fetchAllProducts();
});
