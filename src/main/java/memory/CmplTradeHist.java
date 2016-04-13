package memory;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class CmplTradeHist {

  @QuerySqlField
  String processingDt;

  @QuerySqlField
  String tradeId;

  @QuerySqlField
  String systemCd;

  @QuerySqlField
  String srcSysId;

  @QuerySqlField
  String srcSysTradeId;

  @QuerySqlField
  String srcSysContraTradeId;

  @QuerySqlField
  String srcSubSysId;

  @QuerySqlField
  String srcSubSysTradeId;

  @QuerySqlField
  String entryDt;

  @QuerySqlField
  String buySellCd;

  @QuerySqlField
  String ccyCd;

  @QuerySqlField
  String tradeCcyCd;

  @QuerySqlField
  String settleCcyCd;

  @QuerySqlField
  String tradeStatusCd;

  @QuerySqlField
  String inventory;

  @QuerySqlField
  String srcSysAcct;

  @QuerySqlField
  String srcSysCustAcct;

  @QuerySqlField
  String cusip;

  @QuerySqlField
  String cusip6;

  @QuerySqlField
  String faceAmtTraded;

  @QuerySqlField
  String currFaceAmt;

  @QuerySqlField
  String currMarketValueCcy;

  @QuerySqlField
  String currMarketValueUsd;

  @QuerySqlField
  String factor;

  @QuerySqlField
  String principalAmtCcy;

  @QuerySqlField
  String principalAmtUsd;

  @QuerySqlField
  String accruedInterestCcy;

  @QuerySqlField
  String accruedInterestUsd;

  @QuerySqlField
  String tradeDtMktPrice;

  @QuerySqlField
  String tradePrice;

  @QuerySqlField
  String eodMktPrice;

  @QuerySqlField
  String mktPriceEffDt;

  @QuerySqlField
  String tradeProfit;

  @QuerySqlField
  String tradeFxRate;

  @QuerySqlField
  String eodFxRateCurrDt;

  @QuerySqlField
  String numOfContracts;

  @QuerySqlField
  String productId;

  @QuerySqlField
  String symbol;

  @QuerySqlField
  String assetClass;

  @QuerySqlField
  String assetSubClass;

  @QuerySqlField
  String tradeTypeCd;

  @QuerySqlField
  String settleDt;

  @QuerySqlField
  String maturityDt;

  @QuerySqlField
  String couponRate;

  @QuerySqlField
  String expireDt;

  @QuerySqlField
  String deliveryDt;

  @QuerySqlField
  String isin;

  @QuerySqlField
  String cins;

  @QuerySqlField
  String sedol;

  @QuerySqlField
  String secFee;

  @QuerySqlField
  String sec20EligibleInd;

  @QuerySqlField
  String tbaInd;

  @QuerySqlField
  String callPutInd;

  @QuerySqlField
  String futureOptInd;

  @QuerySqlField
  String equityInd;

  @QuerySqlField
  String shortSaleInd;

  @QuerySqlField
  String strikePrice;

  @QuerySqlField
  String origTradeId;

  @QuerySqlField
  String relatedTradeId;

  @QuerySqlField
  String executionTime;

  @QuerySqlField
  String frontOfficeSys;

  @QuerySqlField
  String frontOfcSysTradeId;

  @QuerySqlField
  String frontOfcSysRefId1;

  @QuerySqlField
  String blockId;

  @QuerySqlField
  String allocNumber;

  @QuerySqlField
  String cancelDt;

  @QuerySqlField
  String correctedTradeId;

  @QuerySqlField
  String correctedTradeDt;

  @QuerySqlField
  String cancelExecTime;

  @QuerySqlField
  String isCancelCorrected;

  @QuerySqlField
  String cancelReason;

  @QuerySqlField
  String enteredByTraderId;

  @QuerySqlField
  String assignedTraderId;

  @QuerySqlField
  String executingBrokerId;

  @QuerySqlField
  String busAcctId;

  @QuerySqlField
  String busAcctExtlId;

  @QuerySqlField
  String marketCd;

  @QuerySqlField
  String positionGroupId;

  @QuerySqlField
  String salesRepId;

  @QuerySqlField
  String salesRepNbr;

  @QuerySqlField
  String salesPersonName;

  @QuerySqlField
  String capacityCd;

  @QuerySqlField
  String commission;

  @QuerySqlField
  String blotterCd;

  @QuerySqlField
  String subsidiaryId;

  @QuerySqlField
  String exchangeCd;

  @QuerySqlField
  String transferInd;

  @QuerySqlField
  String legalAcctId;

  @QuerySqlField
  String legalAcctName;

  @QuerySqlField
  String legalAcctTaxId;

  @QuerySqlField
  String legalAcctTaxStatus;

  @QuerySqlField
  String salesCredit;

  @QuerySqlField
  String grossCreditAmt;

  @QuerySqlField
  String groupCd;

  @QuerySqlField
  String solicitedInd;

  @QuerySqlField
  String legendCd;

  @QuerySqlField
  String splitTradeInd;

  @QuerySqlField
  String tblInsUser;

  @QuerySqlField
  String tblInsTime;

  @QuerySqlField
  String tblUpdUser;

  @QuerySqlField
  String tblUpdTime;

  @QuerySqlField
  String trailer;

  @QuerySqlField
  String comment;

  @QuerySqlField
  String origSrcSysTradeId;

  @QuerySqlField
  String origSrcSysContraTradeId;

  @QuerySqlField
  String financeType;

  @QuerySqlField
  String returnDt;

  @QuerySqlField
  String correctedSrcSysTradeId;

  @QuerySqlField
  String repoOpenCloseInd;

  @QuerySqlField
  String interestRate;

  @QuerySqlField
  String ispSrcTable;

  @QuerySqlField
  String gmiFirm;

  @QuerySqlField
  String gmiSubClass;

  @QuerySqlField
  String eodUndlyerMktPrice;

  @QuerySqlField
  String cmtFee;

  @QuerySqlField
  String gpfFee;

  @QuerySqlField
  String orfFee;

  @QuerySqlField
  String PRECID;

  @QuerySqlField
  String PTDATE;

  @QuerySqlField
  String PTRACE;

  @QuerySqlField
  String PBS;

  @QuerySqlField
  String PCURSY;

  @QuerySqlField
  String PRR;

  @QuerySqlField
  String PQTY;

  @QuerySqlField
  String PMULTF;

  @QuerySqlField
  String PTPRIC;

  @QuerySqlField
  String PCLOSE;

  @QuerySqlField
  String PMKVAL;

  @QuerySqlField
  String PSDATE;

  @QuerySqlField
  String PSUBTY;

  @QuerySqlField
  String PLTDAT;

  @QuerySqlField
  String PSTRIK;

  @QuerySqlField
  String PCLASS;

  @QuerySqlField
  String PACCT;

  @QuerySqlField
  String PEXCH;

  @QuerySqlField
  String PFIRM;

  @QuerySqlField
  String POFFIC;

  @QuerySqlField
  String PCUSIP;

  @QuerySqlField
  String PCUSP2;

  @QuerySqlField
  String PCTYM;

  @QuerySqlField
  String PSDSC1;

  @QuerySqlField
  String PFC;

  @QuerySqlField
  String PSTYPE;

  @QuerySqlField
  String PPTYPE;

  @QuerySqlField
  String PDELET;

  @QuerySqlField
  String PTYPE;

  @QuerySqlField(index = true)
  String tradeDt;

  public String getCacheKey() {
    return processingDt + ',' + tradeDt + ',' + tradeId;
  }

}
