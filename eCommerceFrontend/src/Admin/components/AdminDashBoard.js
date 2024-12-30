import { Grid } from "@mui/material";
import React from "react";
import Achievement from "./Achievement";
import MonthlyOverview from "./MonthlyOverview";
import ProductsTable from "./ProductsTable";
import OrdersTableView from "../View/OrderTableView";
import ProductsTableView from "../View/ProductTableView";

const AdminDashBoard = () => {
  return (
    <div className="p-10">
      <Grid container spacing={2} gap={0}>
        <Grid item xs={12} md={4}>
          <div className="shadow-lg shadow-gray-600">
            <Achievement />
          </div>
        </Grid>
        <Grid item xs={12} md={8}>
          <div className="shadow-lg shadow-gray-600">
            <MonthlyOverview />
          </div>
        </Grid>
        <Grid item xs={12} md={6}>
          <div className="shadow-lg shadow-gray-600">
            <ProductsTableView />
          </div>
        </Grid>
        <Grid item xs={12} md={6}>
          <div className="shadow-lg shadow-gray-600">
            <OrdersTableView />
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default AdminDashBoard;
