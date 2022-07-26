import styled from "styled-components";

import { OverflowBox } from './common';

export const PlaylistInformationBox = styled.div`
  color: gray;
  font-size: small;
`;
export const PlaylistYoutuberBox = styled.div`
  color: gray;
  font-size: small;
`;
export const PlaylistTitleBox = styled(OverflowBox)`
  color: white;
`;

export const IndexVideoMain = styled.main`
  flex-wrap: wrap;
  display: flex;

  & > * {
    padding: 10px;
    width: 400px;
  }
`;
export const IndexVideoImg = styled.img`
  width: 400px;
`;
