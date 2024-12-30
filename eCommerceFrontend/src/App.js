import AdminRouters from "./Routers/AdminRouters";
import CustomerRouters from "./Routers/CustomerRouters";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/*" element={<CustomerRouters />}></Route>
        <Route path="/admin/*" element={<AdminRouters/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
