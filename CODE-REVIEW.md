# Code review summary

1. application-test.properties jeśli jest to plik properties testowy to powinien być w katalogu 'test'
2. w kodzie (np. klasy SwaggerConfig, SwaggerController) widzę sporo magic string, wprawdzie jasno wynika o co chodzi ale jest to zła praktyka. Warto w takiej sytuacji skorzystać ze stałych lub typu wyliczeniowego.
3. nie jest dorym pomysłem ale dane inicująjce aplikacje trzymać w postaci klasy SampleDataInitializer
4. nazwy pakietów niezbyt wiele mówią o logice biznesowej, przemyślałbym organizacje pakietów
5. brakuje testów (dużo więcej testów)