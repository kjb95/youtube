import styled from 'styled-components';

export const NavigationBox = styled.div`
  font-weight: bold;
  display:flex;
  white-space:nowrap;
`;
export const NavigationLeftBox = styled.div`
    margin: auto auto auto 3%;
`;
export const NavigationRightBox = styled.div`
  margin: auto 10% auto auto;
  & > * {
    margin-left: 10%;
  }
`;

export const SelectLanguageSpan = styled.span`
  cursor: pointer;
  color: ${props => props.language === props.currentLanguage ? 'white' : 'gray'};
`;

export const HomeSpan = styled.span`
  font-size: xx-large;
  color: white; 
  cursor: pointer;
`;

export const LoginButtonSpan = styled.span`
  color: white; 
  cursor: pointer;
  font-size: xx-large;
  margin-left: 20%;
`;