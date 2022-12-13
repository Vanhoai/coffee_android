package com.example.coffee.screens.bottom.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.callbacks.OrderCallback;
import com.example.coffee.callbacks.ShopDetailCallback;
import com.example.coffee.models.Order.Order;
import com.example.coffee.models.Order.OrderResponse;
import com.example.coffee.models.Order.ProductOrder;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.ProductDetail;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.Shop.ShopDetailResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Product.CheckOutActivity;
import com.example.coffee.screens.bottom.Product.ProductDetailActivity;
import com.example.coffee.services.OrderService;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class DetailPlaceActivity extends AppCompatActivity {

    private ImageView backNavigation, imageDetailPlace;
    private TextView tvNameDetailPlace, tvTotal, tvDistrict;
    private AppCompatButton btnOrderNow;
    private RecyclerView recycleProducts;
    private ArrayList<Product> products;
    private ShopService shopService;
    private OrderService orderService;
    private Order order;
    private LayoutLoading layoutLoading;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        // init view
        initView();

        // init shared data
        products = new ArrayList<>();

        // init service
        shopService = new ShopService();
        orderService = new OrderService();

        // set view
        render(products);

        // handle click
        handleClick();

        // call api
        detailPlace();
    }

    public void handleClick() {
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Product> productCurrent = new ArrayList<>();

                // check selected product
                for (Product product : products){
                    if (product.getCurrent() > 0){
                        productCurrent.add(product);
                    }
                }

                if (productCurrent.size() <= 0) {
                    Toast.makeText(DetailPlaceActivity.this, "NO PRODUCT SELECTED", Toast.LENGTH_SHORT).show();
                    return;
                }

                // redirect
                Intent intentStart = getIntent();
                Bundle bundleStart = intentStart.getExtras();
                int id = bundleStart.getInt("id",-1);

                Intent intent = new Intent(DetailPlaceActivity.this, CheckOutActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("products", productCurrent);
                bundle1.putInt("id", id);
                bundle1.putSerializable("OrderDetail", order);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order != null) {
                    boolean check = true;
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getCurrent() > 0) {
                            check = false;
                        }
                    }

                    if (check) {
                        layoutLoading.setLoading();
                        orderService.deleteOrder(order.getId(), new OrderCallback() {
                            @Override
                            public void onSuccess(boolean value, OrderResponse orderResponse) {
                                Logger.log("order",order);
                                layoutLoading.setGone();
                                Intent intent1 = new Intent(DetailPlaceActivity.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }

                            @Override
                            public void onFailed(boolean value) {
                                Logger.log("onFailed","onFailed");
                                layoutLoading.setGone();
                            }
                        });
                    } else {
                        Intent intent1 = new Intent(DetailPlaceActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                } else {
                    Intent intent1 = new Intent(DetailPlaceActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }

            }
        });
    }

    public void initView() {
        btnOrderNow = findViewById(R.id.btnOrderNow);
        recycleProducts = findViewById(R.id.recycleProducts);
        backNavigation = findViewById(R.id.backNavigation);
        imageDetailPlace =findViewById(R.id.imageDetailPlace);
        tvNameDetailPlace = findViewById(R.id.tvNameDetailPlace);
        tvDistrict = findViewById(R.id.tvDistrict);
        tvTotal = findViewById(R.id.tvTotal);
        ConstraintLayout constraintLayout = findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, DetailPlaceActivity.this);
        layoutLoading.setGone();
        tvTotal.setText("0 VND");
    }

    public ArrayList<Product> getProducts(Order order) {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductOrder productOrder: order.getProducts()) {
            Product product = new Product();
            product.setId(productOrder.getProduct().getId());
            product.setCurrent(productOrder.getCount());
            product.setImage(productOrder.getProduct().getImage());
            product.setDescription(productOrder.getProduct().getDescription());
            product.setPrice(productOrder.getProduct().getPrice());
            product.setName(productOrder.getProduct().getName());
            product.setRating(productOrder.getProduct().getRating());
            products.add(product);
        }
        return products;
   }

    public void detailPlace(){
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            int id = bundle.getInt("id",-1);

            shopService.getDetail(id, new ShopDetailCallback() {
                @Override
                public void onSuccess(boolean value, ShopDetailResponse shopDetailResponse) {
                    Logger.log("RESPONSE", shopDetailResponse);
                    tvNameDetailPlace.setText("Aurora Coffee");
                    tvDistrict.setText(shopDetailResponse.getShopDetail().getLocation());
                    Glide.with(DetailPlaceActivity.this).load(shopDetailResponse.getShopDetail().getImage()).into(imageDetailPlace);

                    order = (Order) bundle.getSerializable("OrderDetail");
                    ArrayList<Product> productContainOrder = new ArrayList<>();
                    if (order != null) {
                        productContainOrder = getProducts(order);
                    }

                    for (ProductDetail productDetail : shopDetailResponse.getShopDetail().getProducts()){
                        Product item = productDetail.getProduct();

                        for (Product product: productContainOrder) {
                            Logger.log("PRODUCT ORDER ID", product.getId());
                            Logger.log("PRODUCT ID", productDetail.getProduct().getId());
                            if (product.getId() == productDetail.getProduct().getId()) {
                                item.assign(product);
                            }
                        }
                        products.add(item);
                    }

                    render(products);
                }

                @Override
                public void onFailed(boolean value) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void render(ArrayList<Product> data) {


      try {
          // user

          User user = UserInformation.getUser(DetailPlaceActivity.this);

          // shop
          Intent intent = getIntent();
          Bundle bundle = intent.getExtras();
          int id = bundle.getInt("id",-1);

          int orderId = -1;
          if (order != null) {
              orderId = order.getId();
          }

          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailPlaceActivity.this) {
              @Override
              public boolean canScrollVertically() {
                  return false;
              }
          };
          recycleProducts.setLayoutManager(linearLayoutManager);
          RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(DetailPlaceActivity.this, data, "PLACE", id, orderId, new RecycleProductDetailAdapter.OnClick() {
              @Override
              public void onClick(Product product) {
                  Intent intentStart = getIntent();
                  Bundle bundleStart = intentStart.getExtras();
                  int id = bundleStart.getInt("id",-1);

                  Intent intent = new Intent(DetailPlaceActivity.this, ProductDetailActivity.class);
                  Bundle bundle =new Bundle();
                  bundle.putInt("id", product.getId());
                  bundle.putString("status", "PLACE");
                  bundle.putSerializable("OrderDetail", order);
                  bundle.putInt("shop", id);
                  intent.putExtras(bundle);
                  startActivity(intent);
                  finish();
              }
          }, new RecycleProductDetailAdapter.UpdateTotal() {
              @Override
              public void update(ArrayList<Product> products) {
                  float total = 0;
                  for (int i = 0; i < products.size(); i++) {
                      total += products.get(i).getPrice() * products.get(i).getCurrent();
                  }

                  tvTotal.setText(String.format("%.1f VND", total));
              }
          }, new RecycleProductDetailAdapter.UpdateOrder() {
              @Override
              public void update(ArrayList<Product> products, Product product, int change) {
                  layoutLoading.setLoading();
                  if (order == null) {
                      if (change == -1) {
                          return;
                      }
                      orderService.createOrder("", user.getId(), product.getId(), id, change, -1, orderCallback);
                  } else {
                      orderService.createOrder("", user.getId(), product.getId(), id, change, order.getId(), orderCallback);
                  }
              }
          });
          recycleProducts.setAdapter(adapter);
      }catch (Exception exception){
          exception.printStackTrace();
      }
    }

    private final OrderCallback orderCallback = new OrderCallback() {
        @Override
        public void onSuccess(boolean value, OrderResponse orderResponse) {
            Logger.log("ORDER RESPONSE", orderResponse);
            layoutLoading.setGone();
            if (order == null) {
                int id = orderResponse.getOrder().getId();
                Shop shop = orderResponse.getOrder().getShop();
                String address = orderResponse.getOrder().getAddress();
                int status = orderResponse.getOrder().getStatus();
                User user = orderResponse.getOrder().getUser();
                Double total = orderResponse.getOrder().getTotal();
                ArrayList<ProductOrder> list = orderResponse.getOrder().getProducts();
                order = new Order(id, address, shop, total, status, user, list);
            } else {
                Order order1 = orderResponse.getOrder();
                order.assign(order1);
            }
        }

        @Override
        public void onFailed(boolean value) {
            Logger.log("ORDER RESPONSE", "ERROR");
            layoutLoading.setGone();
        }
    };
}