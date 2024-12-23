import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { COMMON_API_ADRESS } from 'commonConstants';
import { IUser } from 'models';
import { HYDRATE } from 'next-redux-wrapper';

export const resultApi = createApi({
  reducerPath: 'resultApi',
  baseQuery: fetchBaseQuery({baseUrl: `${COMMON_API_ADRESS}`}),
  extractRehydrationInfo(action, { reducerPath }) {
    if (action.type === HYDRATE) {
      return action.payload[reducerPath];
    }
  },
  tagTypes: [],
  endpoints: (build) => ({
    addResult: build.mutation<any, any>({
      query: (body) => ({
        url: '/result',
        method: 'POST',
        headers: {
          'Access-Control-Allow-Origin': '*'
        },
        body
      }),
    }),
    getResult: build.query({
      query: (_id) => ({
        url: `/result`,
        params: {
          _id
        }
      })
    }),
    // TODO
    getResults: build.query<any, void>({
      query: () => '/results'
    }),
    getRecentResults: build.query({
      query: (limit) => ({
        url: `/recent`,
        params: {
          limit
        }
      })
    }),
    getTopResults: build.query({
      query: ({days, limit}) => ({
        url: `/top`,
        params: {
          days,
          limit
        }
      })
    }),
    restoreResult: build.mutation<any, any>({
      query: (email: string) => ({
        url: '/restore',
        method: 'POST',
        headers: {
          'Access-Control-Allow-Origin': '*'
        },
        body: {
          email
        }
      }),
    })
  })
});

export const {
  useAddResultMutation,
  useGetResultQuery,
  useGetResultsQuery,
  util: { getRunningOperationPromises },
  useRestoreResultMutation
} = resultApi;


// export endpoints for use in SSR
export const {
  getResults,
  getResult,
  getRecentResults,
  getTopResults,
  restoreResult,
} = resultApi.endpoints;
