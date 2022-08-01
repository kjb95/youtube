import React from "react";
import SelectLanguage from "./select_language.jsx";
import {
  NavigationBox,
  NavigationLeftBox, NavigationRightBox
} from "../../style/styled_component/navigation";
import Home from "./home.jsx";

function Navigation({language, setLanguage}) {
  return (
      <NavigationBox>
        <NavigationLeftBox>
          <Home/>
        </NavigationLeftBox>
        <NavigationRightBox>
          <SelectLanguage language={language} setLanguage={setLanguage} />
        </NavigationRightBox>
      </NavigationBox>
  );
}

export default Navigation;