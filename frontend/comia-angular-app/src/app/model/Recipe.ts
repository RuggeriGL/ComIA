
  export interface Recipe {
    vegetarian: boolean
    vegan: boolean
    glutenFree: boolean
    dairyFree: boolean
    veryHealthy: boolean
    cheap: boolean
    veryPopular: boolean
    sustainable: boolean
    lowFodmap: boolean
    weightWatcherSmartPoints: number
    gaps: string
    preparationMinutes: number
    cookingMinutes: number
    aggregateLikes: number
    healthScore: number
    creditsText: string
    license: string
    sourceName: string
    pricePerServing: number
    extendedIngredients: ExtendedIngredient[]
    id: number
    title: string
    readyInMinutes: number
    servings: number
    sourceUrl: string
    image: string
    imageType: string
    summary: string
    cuisines: any[]
    dishTypes: string[]
    diets: string[]
    occasions: string[]
    instructions: string
    analyzedInstructions: any[]
    originalId: any
    spoonacularScore: number
    spoonacularSourceUrl: string
  }
  
  export interface ExtendedIngredient {
    id: number
    aisle: string
    image: string
    consistency: string
    name: string
    nameClean: string
    original: string
    originalName: string
    amount: number
    unit: string
    meta: string[]
    measures: Measures
  }
  
  export interface Measures {
    us: Us
    metric: Metric
  }
  
  export interface Us {
    amount: number
    unitShort: string
    unitLong: string
  }
  
  export interface Metric {
    amount: number
    unitShort: string
    unitLong: string
  }
  