import React from "react";
import { Route, Routes } from "react-router-dom";
import Cart from "../customer/components/Cart/Cart";
import HomePage from "../customer/pages/HomePage/HomePage";
import Navigation from "../customer/components/Navigation/Navigation";
import Footer from "../customer/components/Footer/Footer";
import Product from "../customer/components/Product/Product";
import ProductDetails from "../customer/components/ProductDetails/ProductDetails";
import Checkout from "../customer/components/Checkout/Checkout";
import Order from "../customer/components/Order/Order";
import OrderDetails from "../customer/components/Order/OrderDetails";
import PaymentSuccess from "../customer/components/Payment/PaymentSuccess";
const CustomerRouters = () => {
  return (
    <div>
      <div className="relative z-50">
        <Navigation />
      </div>
      <Routes>
        <Route path="/login" element={<HomePage />}></Route>
        <Route path="/register" element={<HomePage />}></Route>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/cart" element={<Cart />}></Route>
        <Route
          path="/:levelOne/:levelTwo/:levelThree"
          //   http://localhost:3000/women/clothing/top
          element={<Product />}
        ></Route>
        <Route path="/product/:productId" element={<ProductDetails />}></Route>
        <Route path="/checkout?step=2" element={<Checkout />}></Route>

        <Route path="/account/order" element={<Order />}></Route>
        <Route
          path="/account/order/:orderId"
          element={<OrderDetails />}
        ></Route>
        <Route
          path="/payment/:orderId"
          element={<PaymentSuccess />}
        ></Route>
        
      </Routes>

      <div>
        <Footer />
      </div>
    </div>
  );
};
{
  /* <ProductDetails /> */
}
{
  /* <Cart /> */
}
{
  /* <Checkout /> */
}
{
  /* <Order /> */
}
{
  /* <OrderDetails /> */
}
export default CustomerRouters;
