package org.nikosoft.oanda.api

import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.TradeApi.TradesResponse
import ApiModel.AccountModel.AccountID
import ApiModel.PrimitivesModel.InstrumentName
import ApiModel.TradeModel.{Trade, TradeID, TradeState}
import ApiModel.TradeModel.TradeState.TradeState
import ApiModel.TransactionModel.TransactionID

import scalaz._

object TradeApi {

  /**
    * @param trades The list of Trade detail objects
    * @param lastTransactionID The ID of the most recent Transaction created for the Account
    */
  case class TradesResponse(trades: Seq[Trade], lastTransactionID: TransactionID)

}

trait TradeApi {

  /**
    * Get a list of Trades for an Account
    * @param accountId Account Identifier [required]
    * @param ids List of Trade IDs to retrieve.
    * @param state The state to filter the requested Trades by.
    * @param instrument The instrument to filter the requested Trades by.
    * @param count The maximum number of Trades to return. [default=50, maximum=500]
    * @param beforeID The maximum Trade ID to return. If not provided the most recent Trades in the Account are returned.
    * @return The list of Trades requested
    */
  def trades(accountId: AccountID,
             ids: Seq[TradeID] = Seq.empty,
             state: TradeState = TradeState.OPEN,
             instrument: InstrumentName,
             count: Int = 50,
             beforeID: Option[TradeID] = None
            ): \/[Error, TradesResponse]

}
