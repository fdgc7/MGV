package com.fdanielgarcia.mygermanvocabulary.data

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.fdanielgarcia.mygermanvocabulary.domain.*

object VocabularyContract {
    // Table contents are grouped together in an anonymous object.
    object MasculineSubstantivesEntry : BaseColumns {
        const val TABLE_NAME = "masculine_substantives"
        const val COLUMN_NAME = "name"
        const val COLUMN_MEANING = "meaning"
    }

    object FeminineSubstantivesEntry : BaseColumns {
        const val TABLE_NAME = "feminine_substantives"
        const val COLUMN_NAME = "name"
        const val COLUMN_MEANING = "meaning"
    }

    object NeuterSubstantivesEntry : BaseColumns {
        const val TABLE_NAME = "neuter_substantives"
        const val COLUMN_NAME = "name"
        const val COLUMN_MEANING = "meaning"
    }

    object VerbsEntry : BaseColumns {
        const val TABLE_NAME = "verbs"
        const val COLUMN_INFINITIVE = "infinitive"
        const val COLUMN_PRESENT = "present"
        const val COLUMN_PAST = "past"
        const val COLUMN_PERFECT = "perfect"
        const val COLUMN_MEANING = "meaning"
    }

    object AdjectivesEntry : BaseColumns {
        const val TABLE_NAME = "adjectives"
        const val COLUMN_ADJECTIVE = "adjective"
        const val COLUMN_MEANING = "meaning"
    }

    object AdverbsEntry : BaseColumns {
        const val TABLE_NAME = "adverbs"
        const val COLUMN_ADVERB = "adverb"
        const val COLUMN_MEANING = "meaning"
    }

    object ConjunctionsEntry : BaseColumns {
        const val TABLE_NAME = "conjunctions"
        const val COLUMN_CONJUNCTION = "conjunction"
        const val COLUMN_MEANING = "meaning"
    }

    object PrepositionsEntry : BaseColumns {
        const val TABLE_NAME = "prepositions"
        const val COLUMN_PREPOSITION = "preposition"
        const val COLUMN_MEANING = "meaning"
    }
}

class VocabularyDB(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 4
        const val DATABASE_NAME = "Vocabulary.db"

        private const val SQL_CREATE_MASCULINE_SUBSTANTIVES =
            "CREATE TABLE ${VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.MasculineSubstantivesEntry.COLUMN_NAME} TEXT, " +
                    "${VocabularyContract.MasculineSubstantivesEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_MASCULINE_SUBSTANTIVES =
            "DROP TABLE IF EXISTS ${VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME}"
        private const val SQL_DELETE_MASCULINE_SUBSTANTIVES =
            "DELETE FROM ${VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME}"

        private const val SQL_CREATE_FEMININE_SUBSTANTIVES =
            "CREATE TABLE ${VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.FeminineSubstantivesEntry.COLUMN_NAME} TEXT, " +
                    "${VocabularyContract.FeminineSubstantivesEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_FEMININE_SUBSTANTIVES =
            "DROP TABLE IF EXISTS ${VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME}"
        private const val SQL_DELETE_FEMININE_SUBSTANTIVES =
            "DELETE FROM ${VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME}"

        private const val SQL_CREATE_NEUTER_SUBSTANTIVES =
            "CREATE TABLE ${VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.NeuterSubstantivesEntry.COLUMN_NAME} TEXT, " +
                    "${VocabularyContract.NeuterSubstantivesEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_NEUTER_SUBSTANTIVES =
            "DROP TABLE IF EXISTS ${VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME}"
        private const val SQL_DELETE_NEUTER_SUBSTANTIVES =
            "DELETE FROM ${VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME}"

        private const val SQL_CREATE_VERBS =
            "CREATE TABLE ${VocabularyContract.VerbsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.VerbsEntry.COLUMN_INFINITIVE} TEXT, " +
                    "${VocabularyContract.VerbsEntry.COLUMN_PRESENT} TEXT, " +
                    "${VocabularyContract.VerbsEntry.COLUMN_PAST} TEXT, " +
                    "${VocabularyContract.VerbsEntry.COLUMN_PERFECT} TEXT, " +
                    "${VocabularyContract.VerbsEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_VERBS =
            "DROP TABLE IF EXISTS ${VocabularyContract.VerbsEntry.TABLE_NAME}"
        private const val SQL_DELETE_VERBS =
            "DELETE FROM ${VocabularyContract.VerbsEntry.TABLE_NAME}"

        private const val SQL_CREATE_ADJECTIVES =
            "CREATE TABLE ${VocabularyContract.AdjectivesEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.AdjectivesEntry.COLUMN_ADJECTIVE} TEXT, " +
                    "${VocabularyContract.AdjectivesEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_ADJECTIVES =
            "DROP TABLE IF EXISTS ${VocabularyContract.AdjectivesEntry.TABLE_NAME}"
        private const val SQL_DELETE_ADJECTIVES =
            "DELETE FROM ${VocabularyContract.AdjectivesEntry.TABLE_NAME}"

        private const val SQL_CREATE_ADVERBS =
            "CREATE TABLE ${VocabularyContract.AdverbsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.AdverbsEntry.COLUMN_ADVERB} TEXT, " +
                    "${VocabularyContract.AdverbsEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_ADVERBS =
            "DROP TABLE IF EXISTS ${VocabularyContract.AdverbsEntry.TABLE_NAME}"
        private const val SQL_DELETE_ADVERBS =
            "DELETE FROM ${VocabularyContract.AdverbsEntry.TABLE_NAME}"

        private const val SQL_CREATE_CONJUNCTIONS =
            "CREATE TABLE ${VocabularyContract.ConjunctionsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.ConjunctionsEntry.COLUMN_CONJUNCTION} TEXT, " +
                    "${VocabularyContract.ConjunctionsEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_CONJUNCTIONS =
            "DROP TABLE IF EXISTS ${VocabularyContract.ConjunctionsEntry.TABLE_NAME}"
        private const val SQL_DELETE_CONJUNCTIONS =
            "DELETE FROM ${VocabularyContract.ConjunctionsEntry.TABLE_NAME}"

        private const val SQL_CREATE_PREPOSITIONS =
            "CREATE TABLE ${VocabularyContract.PrepositionsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${VocabularyContract.PrepositionsEntry.COLUMN_PREPOSITION} TEXT, " +
                    "${VocabularyContract.PrepositionsEntry.COLUMN_MEANING} TEXT)"
        private const val SQL_DROP_PREPOSITIONS =
            "DROP TABLE IF EXISTS ${VocabularyContract.PrepositionsEntry.TABLE_NAME}"
        private const val SQL_DELETE_PREPOSITIONS =
            "DELETE FROM ${VocabularyContract.PrepositionsEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_MASCULINE_SUBSTANTIVES)
        db.execSQL(SQL_CREATE_FEMININE_SUBSTANTIVES)
        db.execSQL(SQL_CREATE_NEUTER_SUBSTANTIVES)
        db.execSQL(SQL_CREATE_VERBS)
        db.execSQL(SQL_CREATE_ADJECTIVES)
        db.execSQL(SQL_CREATE_ADVERBS)
        db.execSQL(SQL_CREATE_CONJUNCTIONS)
        db.execSQL(SQL_CREATE_PREPOSITIONS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DROP_MASCULINE_SUBSTANTIVES)
        db.execSQL(SQL_DROP_FEMININE_SUBSTANTIVES)
        db.execSQL(SQL_DROP_NEUTER_SUBSTANTIVES)
        db.execSQL(SQL_DROP_VERBS)
        db.execSQL(SQL_DROP_ADJECTIVES)
        db.execSQL(SQL_DROP_ADVERBS)
        db.execSQL(SQL_DROP_CONJUNCTIONS)
        db.execSQL(SQL_DROP_PREPOSITIONS)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun emptyTable(tableType: String) {
        when (tableType) {
            "MasculineSubstantives" -> writableDatabase.execSQL(SQL_DELETE_MASCULINE_SUBSTANTIVES)
            "FeminineSubstantives" -> writableDatabase.execSQL(SQL_DELETE_FEMININE_SUBSTANTIVES)
            "NeuterSubstantives" -> writableDatabase.execSQL(SQL_DELETE_NEUTER_SUBSTANTIVES)
            "Verbs" -> writableDatabase.execSQL(SQL_DELETE_VERBS)
            "Adjectives" -> writableDatabase.execSQL(SQL_DELETE_ADJECTIVES)
            "Adverbs" -> writableDatabase.execSQL(SQL_DELETE_ADVERBS)
            "Conjunctions" -> writableDatabase.execSQL(SQL_DELETE_CONJUNCTIONS)
            "Prepositions" -> writableDatabase.execSQL(SQL_DELETE_PREPOSITIONS)
        }
    }

    fun countElements(tableType: String): Int {
        var total = 0

        val tableName = when (tableType) {
            "MasculineSubstantives" -> VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME
            "FeminineSubstantives" -> VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME
            "NeuterSubstantives" -> VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME
            "Verbs" -> VocabularyContract.VerbsEntry.TABLE_NAME
            "Adjectives" -> VocabularyContract.AdjectivesEntry.TABLE_NAME
            "Adverbs" -> VocabularyContract.AdverbsEntry.TABLE_NAME
            "Conjunctions" -> VocabularyContract.ConjunctionsEntry.TABLE_NAME
            "Prepositions" -> VocabularyContract.PrepositionsEntry.TABLE_NAME
            else -> ""
        }

        if (tableName != "") {
            val db = this.readableDatabase
            total = DatabaseUtils.queryNumEntries(db, tableName).toInt()
        }

        return total
    }

    fun insertSubstantive(substantive: Substantive) {
        val db = this.writableDatabase

        when (substantive.gender) {
            Gender.MASCULINE -> {
                val values = ContentValues().apply {
                    put(VocabularyContract.MasculineSubstantivesEntry.COLUMN_NAME, substantive.name)
                    put(
                        VocabularyContract.MasculineSubstantivesEntry.COLUMN_MEANING,
                        substantive.meaning
                    )
                }
                val newRowId = db.insert(
                    VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME,
                    null,
                    values
                )
            }
            Gender.FEMININE -> {
                val values = ContentValues().apply {
                    put(VocabularyContract.FeminineSubstantivesEntry.COLUMN_NAME, substantive.name)
                    put(
                        VocabularyContract.FeminineSubstantivesEntry.COLUMN_MEANING,
                        substantive.meaning
                    )
                }
                val newRowId = db.insert(
                    VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME,
                    null,
                    values
                )
            }
            Gender.NEUTER -> {
                val values = ContentValues().apply {
                    put(VocabularyContract.NeuterSubstantivesEntry.COLUMN_NAME, substantive.name)
                    put(
                        VocabularyContract.NeuterSubstantivesEntry.COLUMN_MEANING,
                        substantive.meaning
                    )
                }
                val newRowId = db.insert(
                    VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME,
                    null,
                    values
                )
            }
        }
    }

    fun getSubstantives(gender: Gender): SubstantiveList {
        val db = this.readableDatabase
        val substantiveList = SubstantiveList()

        val tableName = when (gender) {
            Gender.MASCULINE -> VocabularyContract.MasculineSubstantivesEntry.TABLE_NAME
            Gender.FEMININE -> VocabularyContract.FeminineSubstantivesEntry.TABLE_NAME
            Gender.NEUTER -> VocabularyContract.NeuterSubstantivesEntry.TABLE_NAME
        }

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = when (gender) {
            Gender.MASCULINE -> arrayOf(
                BaseColumns._ID,
                VocabularyContract.MasculineSubstantivesEntry.COLUMN_NAME,
                VocabularyContract.MasculineSubstantivesEntry.COLUMN_MEANING
            )
            Gender.FEMININE -> arrayOf(
                BaseColumns._ID,
                VocabularyContract.FeminineSubstantivesEntry.COLUMN_NAME,
                VocabularyContract.FeminineSubstantivesEntry.COLUMN_MEANING
            )
            Gender.NEUTER -> arrayOf(
                BaseColumns._ID,
                VocabularyContract.NeuterSubstantivesEntry.COLUMN_NAME,
                VocabularyContract.NeuterSubstantivesEntry.COLUMN_MEANING
            )
        }

        val cursor = db.query(
            tableName,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val substantive = when (gender) {
                    Gender.MASCULINE -> Substantive(
                        getString(getColumnIndexOrThrow(VocabularyContract.MasculineSubstantivesEntry.COLUMN_NAME)),
                        gender,
                        getString(getColumnIndexOrThrow(VocabularyContract.MasculineSubstantivesEntry.COLUMN_MEANING))
                    )
                    Gender.FEMININE -> Substantive(
                        getString(getColumnIndexOrThrow(VocabularyContract.FeminineSubstantivesEntry.COLUMN_NAME)),
                        gender,
                        getString(getColumnIndexOrThrow(VocabularyContract.FeminineSubstantivesEntry.COLUMN_MEANING))
                    )
                    Gender.NEUTER -> Substantive(
                        getString(getColumnIndexOrThrow(VocabularyContract.NeuterSubstantivesEntry.COLUMN_NAME)),
                        gender,
                        getString(getColumnIndexOrThrow(VocabularyContract.NeuterSubstantivesEntry.COLUMN_MEANING))
                    )
                }
                substantiveList.add(substantive)
            }
            close()
        }

        return substantiveList
    }

    fun insertVerb(verb: Verb) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(VocabularyContract.VerbsEntry.COLUMN_INFINITIVE, verb.infinitive)
            put(VocabularyContract.VerbsEntry.COLUMN_PRESENT, verb.present)
            put(VocabularyContract.VerbsEntry.COLUMN_PAST, verb.past)
            put(VocabularyContract.VerbsEntry.COLUMN_PERFECT, verb.perfect)
            put(VocabularyContract.VerbsEntry.COLUMN_MEANING, verb.meaning)
        }
        val newRowId = db.insert(VocabularyContract.VerbsEntry.TABLE_NAME,null,values)
    }

    fun getVerbs(): VerbList {
        val db = this.readableDatabase
        val verbList = VerbList()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            VocabularyContract.VerbsEntry.COLUMN_INFINITIVE,
            VocabularyContract.VerbsEntry.COLUMN_PRESENT,
            VocabularyContract.VerbsEntry.COLUMN_PAST,
            VocabularyContract.VerbsEntry.COLUMN_PERFECT,
            VocabularyContract.VerbsEntry.COLUMN_MEANING
        )

        val cursor = db.query(
            VocabularyContract.VerbsEntry.TABLE_NAME,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val verb = Verb(
                    getString(getColumnIndexOrThrow(VocabularyContract.VerbsEntry.COLUMN_INFINITIVE)),
                    getString(getColumnIndexOrThrow(VocabularyContract.VerbsEntry.COLUMN_PRESENT)),
                    getString(getColumnIndexOrThrow(VocabularyContract.VerbsEntry.COLUMN_PAST)),
                    getString(getColumnIndexOrThrow(VocabularyContract.VerbsEntry.COLUMN_PERFECT)),
                    getString(getColumnIndexOrThrow(VocabularyContract.VerbsEntry.COLUMN_MEANING))
                )
                verbList.add(verb)
            }
            close()
        }

        return verbList
    }

    fun insertAdjective(adjective: Adjective) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(VocabularyContract.AdjectivesEntry.COLUMN_ADJECTIVE, adjective.adjective)
            put(VocabularyContract.AdjectivesEntry.COLUMN_MEANING, adjective.meaning)
        }
        val newRowId = db.insert(VocabularyContract.AdjectivesEntry.TABLE_NAME,null,values)
    }

    fun getAdjectives(): AdjectiveList {
        val db = this.readableDatabase
        val adjectiveList = AdjectiveList()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            VocabularyContract.AdjectivesEntry.COLUMN_ADJECTIVE,
            VocabularyContract.AdjectivesEntry.COLUMN_MEANING
        )

        val cursor = db.query(
            VocabularyContract.AdjectivesEntry.TABLE_NAME,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val adjective = Adjective(
                    getString(getColumnIndexOrThrow(VocabularyContract.AdjectivesEntry.COLUMN_ADJECTIVE)),
                    getString(getColumnIndexOrThrow(VocabularyContract.AdjectivesEntry.COLUMN_MEANING))
                )
                adjectiveList.add(adjective)
            }
            close()
        }

        return adjectiveList
    }

    fun insertAdverb(adverb: Adverb) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(VocabularyContract.AdverbsEntry.COLUMN_ADVERB, adverb.adverb)
            put(VocabularyContract.AdverbsEntry.COLUMN_MEANING, adverb.meaning)
        }
        val newRowId = db.insert(VocabularyContract.AdverbsEntry.TABLE_NAME,null,values)
    }

    fun getAdverbs(): AdverbList {
        val db = this.readableDatabase
        val adverbList = AdverbList()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            VocabularyContract.AdverbsEntry.COLUMN_ADVERB,
            VocabularyContract.AdverbsEntry.COLUMN_MEANING
        )

        val cursor = db.query(
            VocabularyContract.AdverbsEntry.TABLE_NAME,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val adverb = Adverb(
                    getString(getColumnIndexOrThrow(VocabularyContract.AdverbsEntry.COLUMN_ADVERB)),
                    getString(getColumnIndexOrThrow(VocabularyContract.AdverbsEntry.COLUMN_MEANING))
                )
                adverbList.add(adverb)
            }
            close()
        }

        return adverbList
    }

    fun insertConjunction(conjunction: Conjunction) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(VocabularyContract.ConjunctionsEntry.COLUMN_CONJUNCTION, conjunction.conjunction)
            put(VocabularyContract.ConjunctionsEntry.COLUMN_MEANING, conjunction.meaning)
        }
        val newRowId = db.insert(VocabularyContract.ConjunctionsEntry.TABLE_NAME,null,values)
    }

    fun getConjunctions(): ConjunctionList {
        val db = this.readableDatabase
        val conjunctionList = ConjunctionList()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            VocabularyContract.ConjunctionsEntry.COLUMN_CONJUNCTION,
            VocabularyContract.ConjunctionsEntry.COLUMN_MEANING
        )

        val cursor = db.query(
            VocabularyContract.ConjunctionsEntry.TABLE_NAME,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val conjunction = Conjunction(
                    getString(getColumnIndexOrThrow(VocabularyContract.ConjunctionsEntry.COLUMN_CONJUNCTION)),
                    getString(getColumnIndexOrThrow(VocabularyContract.ConjunctionsEntry.COLUMN_MEANING))
                )
                conjunctionList.add(conjunction)
            }
            close()
        }

        return conjunctionList
    }

    fun insertPreposition(preposition: Preposition) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(VocabularyContract.PrepositionsEntry.COLUMN_PREPOSITION, preposition.preposition)
            put(VocabularyContract.PrepositionsEntry.COLUMN_MEANING, preposition.meaning)
        }
        val newRowId = db.insert(VocabularyContract.PrepositionsEntry.TABLE_NAME,null,values)
    }

    fun getPrepositions(): PrepositionList {
        val db = this.readableDatabase
        val prepositionList = PrepositionList()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            VocabularyContract.PrepositionsEntry.COLUMN_PREPOSITION,
            VocabularyContract.PrepositionsEntry.COLUMN_MEANING
        )

        val cursor = db.query(
            VocabularyContract.PrepositionsEntry.TABLE_NAME,          // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null         // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val preposition = Preposition(
                    getString(getColumnIndexOrThrow(VocabularyContract.PrepositionsEntry.COLUMN_PREPOSITION)),
                    getString(getColumnIndexOrThrow(VocabularyContract.PrepositionsEntry.COLUMN_MEANING))
                )
                prepositionList.add(preposition)
            }
            close()
        }

        return prepositionList
    }
}
