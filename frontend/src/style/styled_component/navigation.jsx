import styled from "styled-components";

export const NavigationBox = styled.div`
  margin-bottom:3%;
  font-weight: bold;
  display:flex;
`;
export const NavigationLeftBox = styled.div`
    margin: auto auto auto 3%;
`;
export const NavigationRightBox = styled.div`
  margin: auto 3% auto auto;
`;

export const SelectLanguageSpan = styled.span`
  cursor: pointer;
  color: ${props => props.language === props.currentLanguage ? 'white' : 'gray'};
`;

export const HomeSpan = styled.span`
  font-size: xx-large;
  color: white; 
  cursor: pointer;
`