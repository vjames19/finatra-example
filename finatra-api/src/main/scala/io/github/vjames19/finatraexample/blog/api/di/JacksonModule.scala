package io.github.vjames19.finatraexample.blog.api.di

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.twitter.finatra.json.modules.FinatraJacksonModule

/**
  * Created by victor.reventos on 6/6/17.
  */
object JacksonModule extends FinatraJacksonModule {
  override protected val propertyNamingStrategy: PropertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
}
