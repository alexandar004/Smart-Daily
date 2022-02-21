import android.content.Context
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.dataBase.AppDatabase
import com.example.dailysmarts.dataBase.DataBaseQuote
import com.example.dailysmarts.dataBase.QuoteDao

class QuoteService(context: Context) {

    private var quoteDao: QuoteDao = AppDatabase.getInstance(context).quoteDao()

    companion object {

        private var instance: QuoteService? = null
        fun getInstance(context: Context): QuoteService {
            if (instance == null) {
                instance = QuoteService(context)
            }
            return instance as QuoteService
        }
    }

    suspend fun getAllQuotes(): List<DataBaseQuote> {
        return quoteDao.getAllQuotes()
    }

    suspend fun addQuote(quote: DataBaseQuote) {
        quoteDao.insertQuote(quote)
    }

    suspend fun deleteQuote(quote: DataBaseQuote) {
        quoteDao.deleteQuote(quote)
    }
}