package com.example.introducemyself

enum class SignUpEntryType {
    CREAT,
    UPDATE,
    DELETE,
    READ
    ;

     companion object {
         fun getEntryType(ordinal: Int?) : SignUpEntryType {
             return SignUpEntryType.values().firstOrNull {
                 it.ordinal == ordinal
             } ?: CREAT
         }
     }
}